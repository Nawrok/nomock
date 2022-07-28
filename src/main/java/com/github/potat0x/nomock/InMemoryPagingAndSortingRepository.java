package com.github.potat0x.nomock;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.util.Streamable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class InMemoryPagingAndSortingRepository<T, ID> extends InMemoryCrudRepository<T, ID>
        implements PagingAndSortingRepository<T, ID> {

    public InMemoryPagingAndSortingRepository(Supplier<ID> idSupplier) {
        super(idSupplier);
    }

    public InMemoryPagingAndSortingRepository(ID initialId, UnaryOperator<ID> idSuccessorFunction) {
        super(initialId, idSuccessorFunction);
    }

    @Override
    public Iterable<T> findAll(Sort sort) {
        List<T> list = Streamable.of(findAll()).toList();
        return sortedList(sort, list);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        Sort sort = pageable.getSort();
        List<T> list = Streamable.of(findAll(sort)).toList();
        return page(pageable, list);
    }

    private List<T> sortedList(Sort sort, List<T> list) {
        List<T> sortedList = new ArrayList<>(list);
        sortedList.sort((o1, o2) -> ReflectiveComparator.compareObjectsByMultipleFields(o1, o2, sort.toList()));
        return sortedList;
    }

    private Page<T> page(Pageable pageable, List<T> items) {
        if (pageable.isUnpaged()) {
            return new PageImpl<>(items);
        }

        final int pageStart = getPageStart(pageable);
        if (pageStart > items.size()) {
            return new PageImpl<>(new ArrayList<>(), pageable, 0);
        }
        final int pageEnd = getPageEnd(pageable.getPageSize(), items.size(), pageStart);

        List<T> elementsInPage = items.subList(pageStart, pageEnd);
        return new PageImpl<>(elementsInPage, pageable, elementsInPage.size());
    }

    private int getPageStart(Pageable pageable) {
        return pageable.getPageNumber() * pageable.getPageSize();
    }

    private int getPageEnd(int pageSize, int totalElements, int pageStart) {
        return Math.min(totalElements, pageStart + pageSize);
    }

}
