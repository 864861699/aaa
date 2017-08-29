//package com.sixi.utils;
//
//import org.apache.poi.ss.formula.functions.T;
//
//import java.util.List;
//
//public class Page<T> {
//    private List<T> list;
//    private int pageNumber;
//    private int pageSize;
//    private int totalPage;
//    private int totalRow;
//
//    public Page(List<T> list, int pageNumber, int pageSize, int totalPage, int totalRow) {
//        this.list = list;
//        this.pageNumber = pageNumber;
//        this.pageSize = pageSize;
//        this.totalPage = totalPage;
//        this.totalRow = totalRow;
//    }
//
//    public List<T> getList() {
//        return this.list;
//    }
//
//    public int getPageNumber() {
//        return this.pageNumber;
//    }
//
//    public int getPageSize() {
//        return this.pageSize;
//    }
//
//    public int getTotalPage() {
//        return this.totalPage;
//    }
//
//    public int getTotalRow() {
//        return this.totalRow;
//    }
//
//    public boolean isFirstPage() {
//        return this.pageNumber == 1;
//    }
//
//    public boolean isLastPage() {
//        return this.pageNumber == this.totalPage;
//    }
//}
