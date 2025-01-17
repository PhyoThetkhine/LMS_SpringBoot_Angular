export interface Page<T> {
    content: T[]; // List of items
    totalPages: number; // Total number of pages
    totalElements: number; // Total number of items
    size: number; // Number of items per page
    number: number; // Current page number
    numberOfElements: number; // Number of items in the current page
    first: boolean; // Is this the first page?
    last: boolean; // Is this the last page?
    empty: boolean; // Is this page empty?
  }