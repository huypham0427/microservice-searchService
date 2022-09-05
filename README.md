# microservice-searchService

## Hystrix
- [Tutorial for Hystrix](https://www.youtube.com/watch?v=niqew7GPP4k&list=PLqq-6Pq4lTTbXZY_elyGv7IkKrfkSrX5e&index=16)
- Add an annotation **CircuitBreaker** in the **Main Application of the Service**
- In the **Service Controller**, apply @HystrixCommand and Fallback method
- Fallback method: when the circuit breaks, **ONLY** call the Fallback method. Should be simple 
