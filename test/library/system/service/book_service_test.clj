(ns library.system.service.book-service-test
  (:require [clojure.test :refer :all]
            [library.system.service.book-service :refer [add-new-book search-book-by-name]]
            [library.system.dao.books-dao :refer [get-book-by-name-from-db add-new-book-to-db]]))


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
