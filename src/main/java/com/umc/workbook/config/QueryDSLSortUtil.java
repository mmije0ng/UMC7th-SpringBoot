package com.umc.workbook.config;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.types.dsl.ComparablePath;

// QueryDSL 페이지네이션 정렬 메서드
public class QueryDSLSortUtil {

    public static <T> OrderSpecifier<?>[] getSort(Pageable pageable, PathBuilder<T> entityPath) {
        List<OrderSpecifier<?>> orders = new ArrayList<>();

        if (pageable.getSort().isSorted()) {
            for (Sort.Order order : pageable.getSort()) {
                Order direction = order.isAscending() ? Order.ASC : Order.DESC;
                ComparablePath<Comparable> path = entityPath.getComparable(order.getProperty(), Comparable.class);
                orders.add(new OrderSpecifier<>(direction, path));
            }
        }

        return orders.toArray(new OrderSpecifier<?>[0]);
    }
}
