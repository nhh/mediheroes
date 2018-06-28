export class AddressRequest {

  private _street: string;
  private _state: string;
  private _zip: number;
  private _city: string;


  get street(): string {
    return this._street;
  }

  set street(value: string) {
    this._street = value;
  }

  get state(): string {
    return this._state;
  }

  set state(value: string) {
    this._state = value;
  }

  get zip(): number {
    return this._zip;
  }

  set zip(value: number) {
    this._zip = value;
  }

  get city(): string {
    return this._city;
  }

  set city(value: string) {
    this._city = value;
  }
}
