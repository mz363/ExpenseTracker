import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserRegistrationService {

  constructor(private http: HttpClient) { }

  public insertUser(user) {
    return this.http.post("http://localhost:8080/api/v1/user", user, {responseType:'text' as 'json'});
  }
}
