# Legacy-to-cloud struts app replatforming example

## Phase 1: Deploy it as is on PCF
Pivotal Cloud Foundry offers a community buildpack, tomee, which allows us to deploy legacy war apps:

```cf push crud-app --random-route -p crud-1.0-SNAPSHOT.war -b https://github.com/cloudfoundry-community/tomee-buildpack.git```

## Phase 2: Spring Bootification
In phase 2, it's time to add spring boot to the legacy app, but without removing struts. You can deploy it using:

```cf push crud-app --random-route -p crud-1.0-SNAPSHOT.war```

Note that we no longer need to specify the tomee buildpack, as we are now using springboot, which comes with tomcat embeded.


## Phase 3: Adding JPA and database persistence
The legacy app was using an in-memory persistence approach. We are adding Spring JPA and Hibernate to persist data into a MySQL database instance.