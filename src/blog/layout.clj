(ns blog.layout
  (:require [hiccup.page :refer [html5 include-css include-js]]))

(defn card [{global-meta :meta :as opts} & contents]
  [:div.card
   (into [] `(:img {:style "height: 280px; width: 100%; display: block;",
                     :alt "100%x280",
                     :data-src "holder.js/100px280/thumb"}
              ~@contents))])

(defn bootstrap-deps []
  ["//maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
   "//code.jquery.com/jquery-3.1.1.slim.min.js"
   "//cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
   "//maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
   "/css/tomlynch.css"])

(defn head [{global-meta :meta posts :entries :as opts} & deps]
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:content "width=device-width, initial-scale=1, shrink-to-fit=no", :name "viewport"}]
   [:meta {:content (:description global-meta), :name "description"}]
   [:meta {:content (:author global-meta), :name "author"}]
   [:link {:href "/favicon.ico", :rel "icon"}]
   [:title (:site-title global-meta)]
   (for [dep deps]
     (cond (re-matches #".*\.css" dep) (include-css dep)
           (re-matches #".*\.js" dep) (include-js dep)))])

(defn navbar [{global-meta :meta posts :entries :as opts}]
  [:nav.navbar.navbar-toggleable-md.navbar-inverse.bg-inverse.fixed-top
   [:button.navbar-toggler.navbar-toggler-right
    {:aria-label "Toggle navigation",
     :aria-expanded "false",
     :aria-controls "the-navbar",
     :data-target "#the-navbar",
     :data-toggle "collapse",
     :type "button"}
    [:span.navbar-toggler-icon]]
   [:a.navbar-brand {:href "/"} (:site-title global-meta)]
   [:div#the-navbar.collapse.navbar-collapse
    [:ul.navbar-nav.mr-auto
     [:li.nav-item.active
      [:a.nav-link {:href "about.html"} "About" [:span.sr-only "(current)"]]]
     #_[:li.nav-item [:a.nav-link {:href "#"} "Link"]]
     #_[:li.nav-item [:a.nav-link.disabled {:href "#"} "Disabled"]]
     #_[:li.nav-item.dropdown
      [:a#dropdown01.nav-link.dropdown-toggle
       {:aria-expanded "false",
        :aria-haspopup "true",
        :data-toggle "dropdown",
        :href "http://example.com"}
       "Dropdown"]
      #_[:div.dropdown-menu
       {:aria-labelledby "dropdown01"}
       [:a.dropdown-item {:href "#"} "Action"]
       [:a.dropdown-item {:href "#"} "Another action"]
       [:a.dropdown-item {:href "#"} "Something else here"]]]]
    #_[:form.form-inline.my-2.my-lg-0
     [:input.form-control.mr-sm-2
      {:placeholder "Search", :type "text"}]
     [:button.btn.btn-outline-success.my-2.my-sm-0
      {:type "submit"}
      "Search"]]]])

(defn with-navbar [{global-meta :meta posts :entries :as opts} & contents]
  (html5 {:lang "en" :itemtype "http://schema.org/Blog"}
         (apply head (cons opts (bootstrap-deps)))    
         [:body
          (navbar opts)
          (into [] `(:div.container.top-spacer ~@contents))]))

