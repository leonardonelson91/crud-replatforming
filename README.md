# Legacy-to-cloud struts app replatforming example

## Phase 1: Deploy it as is on PCF
Pivotal Cloud Foundry offers a community buildpack, tomee, which allows us to deploy legacy war apps:

```cf push crud-app --random-route -p crud-1.0-SNAPSHOT.war -b https://github.com/cloudfoundry-community/tomee-buildpack.git```