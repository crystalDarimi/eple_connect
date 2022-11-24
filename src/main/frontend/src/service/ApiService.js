import {API_BASE_URL}  from "../api-config";
const ACCESS_TOKEN = "ACCESS_TOKEN";



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
    let headers = new Headers({
        "Content-Type": "application/json",
    });

    //로컬 스토리지에서 ACESS TOKEN 가져오기
    const accessToken = localStorage.getItem("ACCESS_TOKEN");
    if(accessToken && accessToken !== null){
        headers.append("Authorization","Bearer"+accessToken);
    }

    let options = {
        headers: headers,
        url: API_BASE_URL + api,
        method: method,
    };
    if (request) {
        // GET method
        options.body = JSON.stringify(request);
    }
    return fetch(options.url, options)
        .then((response) =>
            response.json().then((json) => {
                if (!response.ok) {
                    // response.ok가 true이면 정상적인 리스폰스를 받은것, 아니면 에러 리스폰스를 받은것.
                    return Promise.reject(json);
                }
                return json;
            })
        )
        .catch((error) => {
            // 추가된 부분
            console.log(error.status);
            if (error.status === 403) {
              window.location.href = "/login"; // redirect
            }
            return Promise.reject(error);
        });
}

export function signin(userDTO){
    return call("/eple/v1/auth/signin","POST",userDTO)
        .then((response)=>{
            if(response.token){
                //로컬 스토리지에 토큰 저장
                localStorage.setItem("ACCESS_TOKEN",response.token);

                //token이 존재하는 경우 lecture 화면으로 redirect
                window.location.href="/";
            }
        });
}

export function signout(){
    localStorage.setItem("ACCESS_TOKEN",null);
    window.location.href("/login");
}

