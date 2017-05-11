(ns blog.paginate
  (:require [hiccup.page :refer [html5 include-css]]
            [blog.layout :refer [with-navbar]]))

(defn render [{global-meta :meta posts :entries entry :entry :as opts}]
  (with-navbar opts
    [:h1 (str "Page " (:page entry))]
    [:ul.items.columns.small-12
     (for [post posts]
       [:li (:title post)])]))
