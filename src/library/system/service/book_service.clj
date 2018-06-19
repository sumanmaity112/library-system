(ns library.system.service.book-service
  (:require [library.system.dao.books-dao :as books-dao]))

(defn add-new-book [^String name ^Integer no-of-copies]
  (select-keys (first (books-dao/add-new-book name no-of-copies)) [:id :name]))

(defn get-book [^String name]
  (books-dao/get-book name))