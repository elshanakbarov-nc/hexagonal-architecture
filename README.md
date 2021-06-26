# Route-to-go

### Getdiyin yollari deyish!


Route-to-go is a trip sharing app. It's brought to life several days ago and its future is a big mystery. 


### Setup

In order to setup infra run following command. Then start spring application as usual


```sh
./infra-setup.sh infra up 
```

In order to tear down infra run following command
```sh
./infra-setup.sh infra down
```

### Testing

After successful deployment run following CURL commands

```sh
curl -X GET http://localhost:8090/api/v1/balances?accountId=1
```








