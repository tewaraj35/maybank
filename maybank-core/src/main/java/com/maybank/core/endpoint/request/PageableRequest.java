package com.maybank.core.endpoint.request;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tewaraj
 */
public class PageableRequest extends BaseRequest {

    /**
     * dynamic search on all listed columns
     */
    private String filter;

    /**
     * column order - ascending or descending
     */
    private String order;

    /**
     * fetch size
     */
    private Integer limit;

    /**
     * current paginated page number
     */
    private Integer page;

    public static PageRequest buildLatest() {
        return PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "creationDate"));
    }

    public static PageRequest buildDefault() {
        return PageRequest.of(0, Integer.MAX_VALUE, Sort.unsorted());
    }

    public static PageRequest buildCreationDateAsc(Integer size) {
        return PageRequest.of(0, size == null ? 1 : size, Sort.by(Sort.Direction.ASC, "creationDate"));
    }

    public static PageRequest buildCreationDateDesc(Integer size) {
        return PageRequest.of(0, size == null ? 1 : size, Sort.by(Sort.Direction.DESC, "creationDate"));
    }

    public Boolean isAscending() {
        return StringUtils.isEmpty(order) ? null : order.toCharArray()[0] == '-' ? false : true;
    }

    public Boolean isDescending() {
        return isAscending() == null ? null : !isAscending();
    }

    public Sort getSort() {
        if (StringUtils.isEmpty(order)) {
            return Sort.unsorted();
        }

        List<Sort.Order> orders = new ArrayList<>();
        String[] orderArr = order.split(",");
        for (String s : orderArr) {
            orders.add(new Sort.Order(s.toCharArray()[0] == '-' ? Sort.Direction.DESC : Sort.Direction.ASC, s.replace("-", "")));
        }

        return Sort.by(orders);
    }

    public PageRequest getPageRequest() {
        return PageRequest.of(getPage(), getLimit(), getSort());
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getLimit() {
        return limit == null ? Integer.MAX_VALUE : limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page == null || page <= 0 ? 0 : page - 1;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public static final class Builder {
        private String filter;
        private String order;
        private Integer limit;
        private Integer page;

        private Builder() {
        }

        public static Builder instance() {
            return new Builder();
        }

        public Builder withFilter(String filter) {
            this.filter = filter;
            return this;
        }

        public Builder withOrder(String order) {
            this.order = order;
            return this;
        }

        public Builder withLimit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder withPage(Integer page) {
            this.page = page;
            return this;
        }

        public PageableRequest build() {
            PageableRequest pageableRequest = new PageableRequest();
            pageableRequest.setFilter(filter);
            pageableRequest.setOrder(order);
            pageableRequest.setLimit(limit);
            pageableRequest.setPage(page);
            return pageableRequest;
        }
    }
}
