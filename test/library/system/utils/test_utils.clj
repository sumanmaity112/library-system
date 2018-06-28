(ns library.system.utils.test-utils
  (:require [library.system.dbconfig.database-configuration :refer [db-config]]
            [clojure.java.jdbc :refer [execute!]]
            [clojure.edn :as edn]
            [clojure.java.io :refer [reader]])
  (:import (java.io PushbackReader)))

(defn create-book-info-table []
  (execute! db-config ["CREATE TABLE book_info (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    no_of_copies INTEGER,
    date_created TIMESTAMP default (now() at time zone 'utc'),
    date_changed TIMESTAMP default NULL)"]))

(defn drop-book-info-table [] (execute! db-config ["DROP TABLE IF EXISTS book_info;"]))

(defn load-edn
  "Load edn from an io/reader source (filename or io/resource)."
  [source]
  (print source)
  (with-open [r (reader source)]
    (edn/read (PushbackReader. r)))
  )