(ns library.system.service.book-service-test
  (:require [clojure.test :refer :all]
            [library.system.service.book-service :refer [add-new-book search-book-by-name get-all-books update-book-by-id]]
            [library.system.dao.books-dao :refer [get-book-by-name-from-db add-new-book-to-db get-books update-book]]))


(deftest should-add-a-new-book
  (with-redefs [add-new-book-to-db (fn [name no-of-copies] (lazy-seq (list {:id           7
                                                                            :name         name
                                                                            :no_of_copies no-of-copies
                                                                            :date_created nil
                                                                            :date_changed nil})))]
    (let [result (add-new-book "Lord" 10)]
      (are [expected actual] (= expected actual)
                             "Lord" (get result :name)
                             7 (get result :id)))))

(deftest should-search-for-a-book-with-name
  (with-redefs [get-book-by-name-from-db (fn [name] (list {:name name :no_of_copies 2}))]
    (let [result (search-book-by-name "some_book")]
      (are [expected actual] (= expected actual)
                             (list {:name "some_book", :no_of_copies 2}) result)
      )))

(deftest should-get-all-books
  (with-redefs [get-books (fn [] (list {:name "one-book" :no_of_copies 2}))]
    (let [result (get-all-books)]
      (are [expected actual] (= expected actual)
                             (list {:name "one-book", :no_of_copies 2}) result)
      )))

(deftest should-update-book-data-for-given-id
  (with-redefs [update-book (fn [id ^String name no_of_copies] (list 1))]
    (let [result (update-book-by-id 1 "some_book" 100)]
      (are [expected actual] (= expected actual)
                             (list 1) result)
      )))
