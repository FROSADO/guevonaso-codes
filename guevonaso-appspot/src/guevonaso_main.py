__author__="guevonaso"
__date__ ="$24-mar-2009 13:53:46$"

from google.appengine.ext import webapp
from google.appengine.api import users
from google.appengine.ext.webapp.util import run_wsgi_app

class MainPage(webapp.RequestHandler):
    def get(self):
        self.response.headers['Content-Type'] = 'text/plain'
        user = users.get_current_user();
        if (user):
            self.response.out.write("Sorry " + user.nickname()+".");
        self.response.out.write ('Page under construction')
        url_login = users.create_login_url(self.request.uri)
        
        
application = webapp.WSGIApplication ([('/',MainPage)]
                                      ,debug=True)


def main():
    run_wsgi_app(application);

if __name__ == "__main__":
    main()