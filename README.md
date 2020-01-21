# Keycloak demo (Spring Boot)

This is a demo for an Spring Boot application, using OpenID authentication with Keycloak (https://www.keycloak.org).

You should install and configure Keycloak before to start, according to Keycloak documentation. Some tips:

 * Create a realm
 * Create a client in this realm
 * Create two users and two roles (**user1**, **user2** and **USER1**, **USER2**)
 * Don't forget to add _http:/localhost:8080_ to 'Web-Origins'.
 * Add _http:/localhost:8080/*_ to 'Valid Redirect URIs'
 * 'Access type': public
 * 'Standard flow enabled': true
 
 Update application.properties according to your client and realm configuration.
  
 There are three API endpoints:
 
 * /api/user1 - for USER1 role
 * /api/user2 - for USER2 role
 * /api/nonauth - for unauthenticated user.
 
 For authenticated API call, frontend application should obtain bearer JWT token from Keycloak server and set it to Authorization header.
 An example of the frontend is here - https://github.com/utamons/keycloak-angular8-demo
