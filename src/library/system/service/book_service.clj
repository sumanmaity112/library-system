(ns library.system.service.book-service
    (:require [library.system.dao.books-dao
               :refer [add-new-book-to-db get-book-by-name-from-db get-books update-book get-genre-details get-genres]]))

(defn ^:private get-genre-id
  "Get genre id for given genre"
  [genre]
  ((first (get-genre-details genre)) :id))

(defn add-new-book [^String name ^Integer no-of-copies ^String author-name ^String description ^String logo-url ^String genre]
  (select-keys (first (add-new-book-to-db name no-of-copies author-name description logo-url (get-genre-id genre))) [:id :name]))

(defn search-book-by-name [^String name]
  (get-book-by-name-from-db name))

(defn get-all-books [] (get-books))

(defn get-all-genres [] (get-genres) )

(defn update-book-by-id [id ^String name no_of_copies]
  (update-book id name no_of_copies))
