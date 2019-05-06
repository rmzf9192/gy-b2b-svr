(function patch(root) {
  function jsjsFilter(url) {
    if (url.length > 6 && url.substring(url.length - 6) === '.js.js') {
      url = url.substring(0, url.length - 3);
    }
    return url;
  }

  const xhr = root.XMLHttpRequest.prototype;
  const xhrOpen = xhr.open;
  xhr.open = function() {
    var args = Array.prototype.slice.apply(arguments);
    args[1] = jsjsFilter(args[1]);
    xhrOpen.apply(this, args);
  };
  console.log('[EDP-R1K] bug of xhr.open with ".jsjs" fixed.');
})(this);
