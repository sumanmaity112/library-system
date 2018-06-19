(ns library.system.dao.books-dao-it
  (:require [clojure.test :refer :all])
  (:require [library.system.dao.books-dao :refer [get-book-by-name-from-db add-new-book-to-db]]
            [library.system.utils.utils :as utils]))

(defn create-sandbox [predicate] (utils/create-book-info-table) (predicate) (utils/drop-book-info-table))

(use-fixtures :each create-sandbox)

(deftest should-add-a-new-book
  (let [result (first (add-new-book-to-db "service-test" 9)) ]
    (are [expected actual] (= expected actual)
                           "service-test" (result :name)
                           false (nil? (get result :id)))))

(deftest should-search-book-by-name-from-db
  (add-new-book-to-db "service-test" 9)
  (let [result (get-book-by-name-from-db "service-test")]
    (are [expected actual] (= expected actual)
                           (list {:name "service-test" :no_of_copies 9}) result)))

