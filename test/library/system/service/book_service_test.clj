(ns library.system.service.book-service-test
  (:require [clojure.test :refer :all]
            [library.system.service.book-service :refer [add-new-book]]
            [library.system.dao.books-dao :refer [get-book]]
            [library.system.utils.utils :as utils]
            [stubadub.core :refer [with-stub]])
  )

(defn create-sandbox [predicate] (utils/create-book-info-table) (predicate) (utils/drop-book-info-table))

(use-fixtures :once create-sandbox)

(deftest should-add-a-new-book
  (let [result (add-new-book "service-test" 9)]
    (are [expected actual] (= expected actual)
         "service-test" (result :name)
         false (nil? (get result :id)))))

(deftest should-search-for-a-book-with-name
  (with-stub get-book :returns (list {:name "some_book" :no_of_copies 2})
             (are [expected actual] (= expected actual)
                  (list {:name "some_book",:no_of_copies 2}) (get-book "some_book"))))