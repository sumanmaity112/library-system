(ns library.system.dao.books-dao-it
  (:require [clojure.test :refer :all])
  (:require [library.system.dao.books-dao :refer [get-book-by-name-from-db add-new-book-to-db get-books update-book]]
            [library.system.utils.utils :as utils]))

(defn create-sandbox [predicate] (utils/create-book-info-table) (predicate) (utils/drop-book-info-table))

(use-fixtures :each create-sandbox)

(deftest should-add-a-new-book
  (let [result (first (add-new-book-to-db "service-test" 9))]
    (are [expected actual] (= expected actual)
                           "service-test" (result :name)
                           false (nil? (get result :id)))))

(deftest should-search-book-by-name-from-db
  (add-new-book-to-db "service-test" 9)
  (let [result (get-book-by-name-from-db "service-test")]
    (are [expected actual] (= expected actual)
                           (list {:name "service-test" :no_of_copies 9}) result)))

(deftest should-get-all-books-from-db
  (add-new-book-to-db "service-test" 9)
  (add-new-book-to-db "service-test1" 10)
  (let [result (get-books)]
    (are [expected actual] (= expected actual)
                           (list {:name "service-test" :no_of_copies 9}
                                 {:name "service-test1" :no_of_copies 10}) result)))


(deftest should-update-book-with-given-data
  (add-new-book-to-db "service-test" 9)
  (update-book 1 "updated" 10)
  (let [result (get-books)]
    (are [expected actual] (= expected actual)
                           {:name "updated" :no_of_copies 10} (first result))))