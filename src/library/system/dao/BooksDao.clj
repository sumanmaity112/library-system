(ns library.system.dao.BooksDao
  (:require [library.system.dbconfig.DatabaseConfiguration :refer [dbConfig]]
            [clojure.java.jdbc :as jdbc]))

(defn addNewBook [^String dbName, ^Integer noOfCopies]
  (jdbc/insert! dbConfig :book_info
                {:name dbName, :no_of_copies noOfCopies}))
