# geo

Visualise geo coordinates as points on a rotating sphere.

```bash
sbt run

curl 127.0.0.1:8080/geopoints -d '[{"latitude": 51.0, "longitude": 0.0}]' \
                              -H 'content-type: application/json'

open http://127.0.0.1:8080/assets/index.html
```
