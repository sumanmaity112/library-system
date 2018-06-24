(ns library.system.service.borrower-service
    (:require [library.system.dao.borrower-dao
        :refer [borrow]]))

(defn borrow-book [^Integer user_id ^Integer book_id]
    (select-keys (first (borrow user_id book_id)) [:user_id :book_id :borrowed]))