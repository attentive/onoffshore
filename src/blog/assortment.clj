(ns blog.assortment
  (:require [blog.layout :refer [with-navbar]]
            [hiccup.page :refer [html5 include-css]]))

(defn render [{global-meta :meta posts :entries entry :entry :as opts}]
  (with-navbar opts
    [:h1 (str "Page " (:page entry))]
    [:ul.items.columns.small-12
      (for [post posts]
        [:li (:title post)])]))

