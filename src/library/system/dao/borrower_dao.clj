(ns library.system.dao.borrower-dao
  (:require [library.system.dbconfig.database-configuration :refer [db-config]]
            [clojure.java.jdbc :as jdbc]))

(defn borrow [^Integer user_id, ^Integer book_id]
  (jdbc/insert! db-config :book_activity_log
                {:user_id user_id :book_id book_id :borrowed true}))