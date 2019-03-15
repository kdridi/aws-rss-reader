```shell
mvn package && serverless deploy -v
```

```shell
serverless logs -f feed-fetch -t
```

```shell
curl -X POST "https://XXXXXXXXXX.execute-api.eu-west-3.amazonaws.com/dev/feed/fetch" -d '{"location":"https://linuxfr.org/news.atom"}'
```