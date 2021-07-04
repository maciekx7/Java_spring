## Abstract tree

First task was about abstraction. 
I've implemented:
 - Abstract **Tree** contains:
   - **Name**,
   - **Trunk**,
   - List of **Branches**,
   - **grow()** abstract method,
   - **present()** abstract method.
 - **LeafyTree** (inherits from Tree) consist of:
   - List of **leaves**,
   - **grow()** and **present()** methods implementation.
- **Conifer** (inherits from Tree) consist of:
   - List of **needles**,
   - **grow()** and **present()** methods implementation.

I've also written Unit Tests (**TreeTest**, **LeafyTest**, **ConiferTest**) where I check if **grow()** implementation method works as I desired. For example if Tree has Trunk or if there any branches before leaves/needles growing.


## RESTApi

Second task was about RESTApi service. Main object of this task was to save GPS data from mobile devices. 

Main class named `GPSInfo` stores GPS data. It consist of:
  - uniq id (long)
  - deviceId (long)
  - latitude (long)
  - longtitude (long)
  - date (LocalDate) 
  - time (LocalTime)

### API
I've implemented 6 URLs:
  - `GET /gps` - get all records stored in repository,
  - `GET /gps?deviceId=<deviceId>` - get all records related to selected deviceId,
  - `GET /gps?date=<yyyy-MM-dd>` - get all records related to selected date,
  - `GET /gps/{id}` - get GPS record of selected uniq id,
  - `POST /gps` - save new GPS record in repository of JSON body:
    - deviceId (long),
    - latitude (long),
    - longtitude (long).
  - `DELETE /gps?deviceId=<deviceId>` - delete all records related to selected deviceId.


### Unit tests
All API URLs was tested in Unit tests (`src/test/java/com.company.RESTApi/GPSInfoControllerTests`).  
I've checked if API returns desired HTTP Codes on certain actions and also parameters validation send as HTTP body or HTTP parameters.


