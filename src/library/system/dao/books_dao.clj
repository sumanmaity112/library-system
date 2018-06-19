(ns library.system.dao.books-dao
  (:require [library.system.dbconfig.database-configuration :refer [db-config]]
            [clojure.java.jdbc :as jdbc]))

(defn add-new-book-to-db [^String name, ^Integer no-of-copies]
  (jdbc/insert! db-config :book_info
                {:name name, :no_of_copies no-of-copies}))


(defn get-book-by-name-from-db [^String name]
  (jdbc/query db-config
              ["SELECT name,no_of_copies FROM book_info WHERE name = ?" name]))
