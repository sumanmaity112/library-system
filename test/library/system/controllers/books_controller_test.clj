(ns library.system.controllers.books-controller-test
  (:require [clojure.test :refer :all])
  (:require [library.system.controllers.books-controller :refer [app]]
            [ring.mock.request :as mock]
            [library.system.service.book-service :refer [add-new-book search-book-by-name get-all-books update-book-by-id]]))

(deftest should-add-new-book-to-system
  (with-redefs [add-new-book (fn [^String name ^Integer no-of-copies] {:name name :id 1})]
    (let [response (app (-> (mock/request :post "/add") (mock/body {:name "test" :no_of_copies "6"})))]
      (are [expected actual] (= expected actual)
                             200 (response :status)
                             {"Content-Type" "application/json; charset=utf-8"} (response :headers)
                             "{\"name\":\"test\",\"id\":1}" (response :body)
                             )
      )
    ))

(deftest should-search-book-name
  (with-redefs [search-book-by-name (fn [^String name] {:name "test_book" :no_of_copies 2})]
    (let [response
          (app (-> (mock/request :get "/search?name=test_book")))]
      (are [expected actual] (= expected actual)
                             200 (response :status)
                             "{\"name\":\"test_book\",\"no_of_copies\":2}" (response :body)))))

(deftest should-get-all-books
  (with-redefs [get-all-books (fn [] (list {:name "test_book" :no_of_copies 2}))]
    (let [response
          (app (-> (mock/request :get "/books")))]
      (are [expected actual] (= expected actual)
                             200 (response :status)
                             "[{\"name\":\"test_book\",\"no_of_copies\":2}]" (response :body)))))

(deftest should-update-book-with-given-data
  (with-redefs [update-book-by-id (fn [id ^String name no_of_copies] (list 1))]
    (let [response
          (app (-> (mock/request :post "/update") (mock/body {:id 1 :name "test" :no_of_copies "100"})))]
      (are [expected actual] (= expected actual)
                             200 (response :status)
                             "[1]" (response :body)))))

