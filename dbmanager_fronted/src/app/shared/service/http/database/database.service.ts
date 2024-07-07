import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../../../../enviroments/environment";

@Injectable({
  providedIn: 'root'
})
export class DatabaseService {

  constructor(private http: HttpClient) {
  }

  createDatabase(serverName: string | null, databaseName: string) {
    const url = environment.uri + 'database/' + serverName + '/create/' + databaseName;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.post(url, httpOptions);
  }
}
