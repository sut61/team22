import {Component, OnInit ,ViewChild} from '@angular/core';
import {DatePipe} from '@angular/common';
import {MatSort} from '@angular/material';
import { HttpClient} from '@angular/common/http';
import {STOCKINGService} from '../service/stocking.service';
import { DataSource } from '@angular/cdk/collections';
import { Observable } from 'rxjs/Observable';
import {MatSnackBar} from '@angular/material';
export interface Tile {
  cols: number;
  rows: number;
}
export interface StockElement {
  productID: String;
  productName: String;
  productPrice: number;
  productQuantity: number;
  type: {
    typeName: String;
  };
  status: {
    statusProduct: String;
  };
}
@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.css']
})

export class StockComponent implements OnInit {
  tiles: Tile[] = [
    {cols: 2, rows: 1, },
  ];
  tile_right: Tile[] = [
    {cols: 3, rows: 1, },
  ];
  type: Array<any>;
  detail: Array<any>;
  description: Array<any>;
  status: Array<any>;
  product: Array<any>;
  productDate: Array<any>;
  selectproductDate: Array<any>;
  
  pipe = new DatePipe('en-US');
  @ViewChild(MatSort)
  sort: MatSort;

  views: any = {
    productID: '',
    productName: '',
    productQuantity: '',
    productPrice : '',
    statusSelect: '',
    typeSelect: '',
    detailSelect: '',
    prodID : '',
    detID : '',
    data: '',
    selectDesID:'',
    selectProductID: '',
    selectProductName: '',
    selectProductQuantity: '',
    selectProductPrice : '',
    selectStatus : '',
    selectType : '',
    selectDetail: '',
    selectData: '',
    selectPID: ''
  };
  displayedColumns: string[] = ['PID', 'productID', 'productName', 'productPrice', 'productQuantity', 'types', 'statuses'];
  displayedColumnss: string[] = ['dID','PID', 'productID','detID','detail', 'data'];
  dataSource = new StockDataSource(this.STOCKService);
  constructor(private STOCKService: STOCKINGService,private snackBar: MatSnackBar, private httpClient: HttpClient) {
  }
  
  ngOnInit() {
    this.STOCKService.getType().subscribe(data => {
      this.type = data;
      console.log(this.type);
    });
    this.STOCKService.getStatus().subscribe(data => {
      this.status = data;
      console.log(this.status);
    });
    this.STOCKService.getProduct().subscribe(data => {
      this.product = data;
      console.log(this.product);
    });
    this.STOCKService.getDetail().subscribe(data => {
      this.detail = data;
      console.log(this.detail);
    });
    this.STOCKService.getDescription().subscribe(data => {
      this.description = data;
      console.log(this.description);
    });
  }
  delete() {
    this.views.prodID = this.views.selectPID;
    this.httpClient.delete('http://localhost:8080/product/delete/' + this.views.prodID )
    .subscribe(
      data => {
        this.snackBar.open('delete', 'complete', {
        });
        console.log('Delete Request is successful', data);
      },
      error => {
        this.snackBar.open('delete', 'uncomplete', {
        });
        console.log('Error', error);
      }
    );
  }
  deleteDes() {
    this.httpClient.delete('http://localhost:8080/description/delete/' + this.views.selectDesID )
    .subscribe(
      data => {
        this.snackBar.open('delete', 'complete', {
        });
        console.log('Delete Request is successful', data);
      },
      error => {
        this.snackBar.open('delete', 'uncomplete', {
        });
        console.log('Error', error);
      }
      );
  }
   selectRow(row) {
    this.views.selectPID = row.prodId;
    this.views.selectProductID = row.productIds;
    this.views.selectProductName = row.productName;
    this.views.selectProductPrice = row.productPrice;
    this.views.selectProductQuantity = row.productQuantity;
    this.views.selectStatus = row.status.stateId;
    this.views.selectType = row.type.typeIds;
    console.log(this.views.selectPID);
    console.log(this.views.selectProductID);
    console.log( this.views.selectProductName);
    console.log(this.views.selectProductPrice);
    console.log( this.views.selectProductQuantity);
    console.log( this.views.selectStatus);
    console.log(this.views.selectType); 
  }
  electRow(row) {
    this.views.selectDesID = row.descriptionIds;
    this.views.selectPID = row.product.prodId;
    this.views.selectProductID = row.product.productIds;
    this.views.selectDetail = row.detail.detailIds;
    this.views.selectData = row.dataDescription;
    console.log(this.views.selectDesID);
    console.log(this.views.selectPID);
    console.log(this.views.selectProductID);
    console.log(this.views.selectDetail);
    console.log(this.views.selectData);
  }
 updateproduct() {
  this.views.prodID = this.views.selectPID;
  if (!this.views.productID) {
    this.views.productID = this.views.selectProductID;
  }
  if (!this.views.productName) {
    this.views.productName = this.views.selectProductName;
  }
  if (!this.views.productQuantity) {
    this.views.productQuantity = this.views.selectProductQuantity;
  }
  if (!this.views.productPrice) {
    this.views.productPrice = this.views.selectProductPrice;
  }
  if (!this.views.statusSelect) {
    this.views.statusSelect = this.views.selectStatus;
  }
  console.log(this.views.productName);
  console.log(this.views.productQuantity);
  console.log(this.views.typeSelect);
  console.log(this.views.productDate);
  this.httpClient.put('http://localhost:8080/product/updateproduct/' + this.views.prodID + '/'
  + this.views.productID + '/' + this.views.productName + '/' + this.views.productPrice + '/' 
  + this.views.productQuantity + '/' + this.views.selectStatus + '/' + this.views.selectType
  , this.views)
    .subscribe(
      data => {
        if (data)  {
         this.snackBar.open('edit complete', 'complete', {
         });
         console.log('Success');
         window.location.reload();
        }
      },
      error => {
       this.snackBar.open('edit uncomplete', 'uncomplete', {
       });
        console.log('Uncomplete',  error);
      }
    );
  }
  updatedetail() {
    this.views.prodID = this.views.selectPID;
    if (!this.views.detID) {
      this.views.detID = this.views.selectDetail;
    }
    if (!this.views.data) {
      this.views.data = this.views.selectData;
    }
    console.log(this.views.prodID);
    console.log(this.views.detID);
    console.log(this.views.data);
    this.httpClient.put('http://localhost:8080/description/updatedetail/' + this.views.prodID + '/'
    + this.views.detID + '/' + this.views.data, this.views, )
    .subscribe(
      data => {
        if (data)  {
          this.snackBar.open('edit detail ', 'complete', {
          });
          console.log('Success');
        }
      },
      error => {
        this.snackBar.open('edit detail', 'uncomplete', {
        });
        console.log('Uncomplete',  error);
      }
    );
  }
  addproduct() {
    const rex = new RegExp('[P]+[0-9]{1,5}$');
    const pname = new RegExp('[A-Za-zw0-9d]{3,30}');
    this.views.productID.charAt(0);
    console.log(this.views.productID.charAt(0));
    if (this.views.productName === ''|| this.views.productPrice === ''||
      this.views.productQuantity === '' || this.views.productDate === '' ||
      this.views.productID === ''){
      this.snackBar.open('โปรดใส่ข้อมูลให้ครบ', 'OK', {  });
    } else {
        this.STOCKService.CheckProductIDs(this.views.productID).subscribe(checkProductIDs => {
        console.log( checkProductIDs );
        if ( checkProductIDs != null ) {
              this.snackBar.open('Cannot Use ProductID ', 'OK', {});
        }else {     
            if (rex.test(this.views.productID)) {
              if (pname.test(this.views.productName)) {
            this.httpClient.post('http://localhost:8080/product/add/' + this.views.productID + '/' + this.views.productName
            + '/' + this.views.productPrice + '/' + this.views.productQuantity + '/'
            + this.pipe.transform(this.productDate,'dd:MM:yyyy') + '/'
            + this.views.statusSelect + '/' + this.views.typeSelect, this.views)
            .subscribe(
                data => {
                  this.snackBar.open('input ', 'complete', {
                  });
                  console.log('INPUT Request is successful', data);
                },
                error => {
                  this.snackBar.open('input ', 'uncomplete', {
                  });
                  console.log('Error', error);
                }
              );
            }
            else{
              this.snackBar.open('Product Name is Between 3-30 ', 'OK', {
              });
            }
          }else{
              this.snackBar.open('ProductID Fisr Character is P and between 1-99999', 'OK', {
              });
            }
        }
      console.log(this.views.productID);
      console.log(this.views.productName);
      console.log(this.views.productPrice);
      console.log(this.views.productQuantity);
      console.log(this.views.productDate);
      console.log(this.views.statusSelect);
      console.log(this.views.typeSelect);
    });
  }
}
  adddetail() {
    this.views.prodID = this.views.selectPID;
    if (this.views.data === ''||this.views.detID === '')
        {  this.snackBar.open('โปรดใส่ข้อมูลให้ครบ', 'OK', {
            });
        }
    else {     
    this.httpClient.post('http://localhost:8080/description/' +  this.views.prodID + '/' + this.views.detID + '/' + this.views.data,
    this.views, ) .subscribe(
      data => {
        this.snackBar.open('input detail ', 'complete', {
        });
        console.log('INPUT Request is successful', data);
      },
      error => {
        this.snackBar.open('input detail ', 'uncomplete', {
        });
        console.log('Error', error);
      }
    );
  }
  }
}
export class StockDataSource extends DataSource<any> {
  constructor(private stockService: STOCKINGService) {
    super();
  }
  connect(): Observable<StockElement[]> {
    return this.stockService.getProduct();
  }
  disconnect() {}
}