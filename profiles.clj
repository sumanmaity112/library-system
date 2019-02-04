{:profiles/test {:env {:database-name     "library_test",
                       :host-name         "127.0.0.1"
                       :username          "library_admin"
                       :password          "password"
                       :dbtype            "postgres"
                       :driver-class-name "org.postgresql.Driver"}},
 :profiles/dev  {:env {:database-name     "library",
                       :host-name         "127.0.0.1"
                       :username          "library_admin"
                       :password          "password"
                       :dbtype            "postgres"
                       :driver-class-name "org.postgresql.Driver"}}
 }
