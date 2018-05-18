export class CompanyRequest {

  constructor(
    public name : string,
    public ownerId : number,
    public email : string,
    public active : boolean,
    public verified : boolean
  ){

  }
}
