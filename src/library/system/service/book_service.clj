(ns library.system.service.book-service
    (:require [library.system.dao.books-dao
               :refer [add-new-book-to-db get-book-by-name-from-db get-books]]))

(defn add-new-book [^String name ^Integer no-of-copies]
  (select-keys (first (add-new-book-to-db name no-of-copies)) [:id :name]))

(defn search-book-by-name [^String name]
  (get-book-by-name-from-db name))

(defn get-all-books [] (get-books))
