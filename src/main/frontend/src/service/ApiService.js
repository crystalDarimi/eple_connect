import {API_BASE_URL}  from "../api-config";



 /*

export const call = (api, method, request) =>{
    let options = {
        headers: new Headers({
            "Content-Type": "application/json",
        }),
        url: API_BASE_URL + api,
        method: method,
    };
    if (request) {
        // GET method
        options.body = JSON.stringify(request);
    }
    return fetch(options.url, options).then((response) =>
        response.json().then((json) => {
            if (!response.ok) {
                // response.ok가 true이면 정상적인 리스폰스를 받은것, 아니면 에러 리스폰스를 받은것.
                return Promise.reject(json);
            }
            return json;
        })
    );
}



export function call(api,method,request)  {
    let options = {
        headers:new Headers({
            "Content-Type":"application/json",
        }),
        url: API_BASE_URL + api,
        method: method,
    };

    if(request){
        //GET 메서드
        options.body = JSON.stringify(request);
    }
    return fetch(options.url, options).then((response) =>
        response.json().then((json) => {
            if (!response.ok) {
                // response.ok가 true이면 정상적인 리스폰스를 받은것, 아니면 에러 리스폰스를 받은것.
                return Promise.reject(json);
            }
            return json;
        })
    );

}




  */

export function call  (api, method, request) {
    let options = {
        headers: new Headers({
            "Content-Type": "application/json",
        }),
        url: API_BASE_URL + api,
        method: method,
    };
    if (request) {
        // GET method
        options.body = JSON.stringify(request);
    }
    return fetch(options.url, options).then((response) =>{
        if(response.status ===200){
            return response.json();
        }else if(response.status === 403){
            window.location.href = "login"; //redirect
        }else {
            Promise.reject(response);
            throw Error(response);
        }
        }).catch((error)=>{
            console.log("http error");
            console.log(error);
    });
}


