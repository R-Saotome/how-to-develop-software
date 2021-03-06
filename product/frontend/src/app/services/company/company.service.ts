import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { map, single } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Company, SimpleCompany } from 'src/app/interface/company.interface';

const COMPANY_SUFFIX = 'companies';

@Injectable({
  providedIn: 'root',
})
export class CompanyService {
  constructor(private http: HttpClient) {
    this.fetch();
  }

  companyList$: Observable<(Company | SimpleCompany)[]>;

  fetch(): void {
    this.companyList$ = this.http
      .get(`${environment.BASE_API_URL}/${COMPANY_SUFFIX}`)
      .pipe(map((d: any) => d.data));
  }

  getAll(): Observable<(Company | SimpleCompany)[]> {
    return this.companyList$;
  }

  getOptions() {
    return this.companyList$.pipe(
      map((list: Company[]) =>
        list.map((c) => {
          return { id: c.id, name: c.name };
        })
      )
    );
  }

  getById(id: any) {
    return this.companyList$.pipe(
      map((d) => d.filter((c) => c.id === Number(id))),
      single()
    );
  }

  add(company: Company) {
    return this.http.post(
      `${environment.BASE_API_URL}/${COMPANY_SUFFIX}`,
      company
    );
  }

  update(company: Company) {
    return this.http.put(
      `${environment.BASE_API_URL}/${COMPANY_SUFFIX}/${company.id}`,
      company
    );
  }
}
