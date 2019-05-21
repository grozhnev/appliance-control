 
<h4>Examples of how to invoke the REST API (e.g. curl):</h4>


Add new item
curl -d '{item_model}'  http://localhost:8080/add/model=


View all appliances:
    curl -v http://localhost:8080/getAll
 
 
 
Delete item by id:
    curl -d ''  http://localhost:8080/del/{item_id}

Get item by id:
    curl -d '' http://localhost:8080/get/{item_id}

        
        
<h5> Cook dish: </h5>
(!) NOTE: You can't change entity state in wron order.

Follow the workflow:
* turn on
* load dish
* start cooking
* take dish
* turn off
        
<br/>
Turn on item:
    curl -d '' http://localhost:8080/turnOn/{item_id}
    

Put dish inside:
    curl -d '' http://localhost:8080/loadDish/{item_id}
    
Cook dish:
    curl -d '' http://localhost:8080/cook/{item_id}

Take prepared dish:    
    curl -d '' http://localhost:8080/stop/{item_id}
       
       
Turn off appliance entity:
   curl -d '' http://localhost:8080/turnOff/{item_id}
          