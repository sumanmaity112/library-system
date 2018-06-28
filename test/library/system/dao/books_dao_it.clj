(ns library.system.dao.books-dao-it
  (:require [clojure.test :refer :all])
  (:require [library.system.dao.books-dao :refer [get-book-by-name-from-db add-new-book-to-db get-books update-book add-genre get-genre-details]]
            [library.system.utils.test-utils :as utils]
            [library.system.utils.db-utils :refer [migrate rollback-all]]))

(defn create-sandbox [predicate] (migrate) (add-genre "test") (predicate) (rollback-all))
(use-fixtures :each create-sandbox)

(deftest should-add-new-genre-to-system
  (let [result (first (add-genre "test-genre"))]
    (are [expected actual] (= expected actual)
                           "test-genre" (result :genre)
                           false (nil? (get result :id)))))

(deftest should-give-genre-details-for-given-genre
  (let [result (first (get-genre-details "test"))]
    (are [expected actual] (= expected actual)
                           "test" (result :genre)
                           false (nil? (get result :id))
                           )))

(deftest should-add-a-new-book
  (let [result (first (add-new-book-to-db "service-test" 9 "author-name" "test description" "logo-url" 1))]
    (are [expected actual] (= expected actual)
                           "service-test" (result :name)
                           false (nil? (get result :id))
                           "author-name" (result :author_name)
                           "test description" (result :description)
                           "logo-url" (result :logo_url)
                           1 (result :genre_id)
                           )))

(deftest should-search-book-by-name-from-db
  (add-new-book-to-db "service-test" 9 "author-name" "test description" "logo-url" 1)
  (let [result (get-book-by-name-from-db "service-test")]
    (are [expected actual] (= expected actual)
                           (list {:name "service-test" :no_of_copies 9}) result)))

(deftest should-get-all-books-from-db
  (add-new-book-to-db "service-test" 9 "author-name" "test description" "logo-url" 1)
  (add-new-book-to-db "service-test1" 10 "author-name" "test description" "logo-url" 1)
  (let [result (get-books)]
    (are [expected actual] (= expected actual)
                           (list {:name "service-test" :no_of_copies 9}
                                 {:name "service-test1" :no_of_copies 10}) result)))


(deftest should-update-book-with-given-data
  (add-new-book-to-db "service-test" 9 "author-name" "test description" "logo-url" 1)
  (update-book 1 "updated" 10)
  (let [result (get-books)]
    (are [expected actual] (= expected actual)
                           {:name "updated" :no_of_copies 10} (first result))))