'use strict';

const R_BASE = '/srm';
const H_XSRF = 'el-xsrf';
const R_CODE = 'el-result-code';

const avoid = () => Promise.resolve(void 0);
const log = console.log ? console.log.bind(console) : alert;

function errHandle(err) {
  const msg = '[EDP] error happened: ' + err.message;
  console && console.error ? console.error(msg) : alert(msg);
}

function resHandle(res) {
  const http = res.status;
  const type = res.headers.get('Content-Type');
  const json = http === 200 && type && type.startsWith('application/json'); // startsWith - polyfill.io
  const code = res.headers.get(R_CODE); // log('[EDP] code:', code);
  return (json ? res.json() : avoid()).then(json => ({ http, code, json }));
}
function resHandleForPost(res) {
  const data = resHandle(res);
  if (!data.code) return data;
  // EDP response handling
  const { http, code, json } = data;
  const ok = http === 200 && code === 'OK';
  log(ok ? '[EDP] OK' : '[EDP] NG:', code, json);
  return { ok, http, code, json };
}

function toURL(path, params) {
  const qs = new URLSearchParams(params).toString();
  return path + (params ? '?' + qs : '');
}

function buildFetchOptions() {
  return {
    credentials: 'same-origin',
    headers: { H_XSRF: localStorage.getItem(H_XSRF) },
  };
}
function _get(path, params) {
  const opts = buildFetchOptions();
  return fetch(toURL(path, params), opts)
    .then(resHandle)
    .catch(errHandle);
}

function toPostOptions(payload, contentType) {
  const opts = buildFetchOptions();
  opts.method = 'POST';
  if (typeof payload === 'object' && payload.constructor === Object) {
    opts.headers['Content-Type'] = 'application/json';
    opts.body = JSON.stringify(payload);
  } else {
    opts.body = payload;
  }
  return opts;
}
function _post(path, payload) {
  var opts = toPostOptions(payload);
  return fetch(toURL(path), opts)
    .then(resHandleForPost)
    .catch(errHandle);
}
function _form(path, payload) {
  return _post(path, new URLSearchParams(payload));
}

function mold(baseURL = '') {
  const url = path => baseURL + path;
  const get = (path, params) => _get(url(path), params);
  const post = (path, payload) => _post(url(path), payload);
  const form = (path, payload) => _form(url(path), payload);
  const xsrf = xsrfURL => get(xsrfURL).then(({ code }) => localStorage.setItem(H_XSRF, code));
  return { log, url, get, post, form, xsrf, mold };
}

module.exports = mold(R_BASE);
