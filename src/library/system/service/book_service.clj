(ns library.system.service.book-service
    (:require [library.system.dao.books-dao
               :refer [add-new-book-to-db get-book-by-name-from-db get-books update-book]]))

(defn add-new-book [^String name ^Integer no-of-copies]
  (select-keys (first (add-new-book-to-db name no-of-copies)) [:id :name]))

(defn search-book-by-name [^String name]
  (get-book-by-name-from-db name))

(defn get-all-books [] (get-books))

(defn update-book-by-id [id ^String name no_of_copies]
  (update-book id name no_of_copies))
