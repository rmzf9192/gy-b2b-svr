# [Fetch API](https://developer.mozilla.org/zh-CN/docs/Web/API/Fetch_API/Using_Fetch)

```js
  // Default options are marked with *
  const opts = {
    method: 'POST', // *GET, POST, PUT, DELETE, etc.
    cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
    credentials: 'same-origin', // include, same-origin, *omit
    headers: {
      'User-Agent': 'Mozilla/4.0 MDN Example',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(payload), // must match 'Content-Type' header
    mode: 'cors', // no-cors, cors, *same-origin
    redirect: 'follow', // manual, *follow, error
    referrer: 'no-referrer', // *client, no-referrer
  }
```

## [Example](https://github.github.io/fetch/)

```js
  fetch(url, {
    method: "POST",
    body: JSON.stringify(data),
    headers: {
      "Content-Type": "application/json"
    },
    credentials: "same-origin"
  }).then(function(response) {
    response.status     //=> number 100–599
    response.statusText //=> String
    response.headers    //=> Headers
    response.url        //=> String

    return response.text()
  }, function(error) {
    error.message //=> String
  })
```

## `options` Configuration

### `headers`

#### `Content-Type`

Default value:

* If body is String           - text/plain;charset=UTF-8
* If body is URLSearchParams  - application/x-www-form-urlencoded;charset=UTF-8
* If body is FormData         - multipart/form-data
* If body is Blob             - inherited from the blob.type property

### `credentials`

Authentication credentials mode. Default: "omit"

* "omit" - don't include authentication credentials (e.g. cookies) in the request
* "same-origin" - include credentials in requests to the same site
* "include" - include credentials in requests to all sites
