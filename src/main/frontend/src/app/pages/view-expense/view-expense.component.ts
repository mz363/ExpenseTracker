import { Component, OnInit } from '@angular/core';
import { ExpenseService } from 'src/app/service/expense/expense.service';
import { Chart } from 'chart.js';  
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-expense',
  templateUrl: './view-expense.component.html',
  styleUrls: ['./view-expense.component.css']
})
export class ViewExpenseComponent implements OnInit {

  expenses: any;
  category: String;
  apply: String = "";

  dateFormatted = [];

  Cost = []
  Category = []

  chart = []
  // LineChart = [];

  CostData = []
  CategoryData = []

  deleteToggle = true;

  Date = []
  DateData = []

  DateMonthly = []
  CostMonthlyData = []
  DateMonthlyData = []
  DateMonthlyLabel = []
  CostMonthlyDataLabel = []

  constructor(private service:ExpenseService, private router: Router) { }

  ngOnInit() {
    this.init();
  }

  public init() {
    this.selectedPage();
    this.getAllExpenses();
    this.doughnutChart();
    this.lineChart();
  }

  changeApply(){
    this.apply = "";
  }

  hideDelete(event):any{
    event = event || window.event; // IE
    var target = event.target || event.srcElement; // IE

    var id = target.id;
    document.getElementById(id).style.display = "none";
    document.getElementById("saveButton").style.display = "block";
  }

  hideSave(event):any{
    event = event || window.event; // IE
    var target = event.target || event.srcElement; // IE

    var id = target.id;
    document.getElementById(id).style.display = "none";
    document.getElementById("deleteButton").style.display = "block";
  }

  public selectedPage(){
    if(this.router.url == "/view-expense") {
      document.getElementById("expenseLabel").style.border = "1px solid navy";
      document.getElementById("expenseRoute").style.pointerEvents = "none";
      document.getElementById("expenseRoute").style.cursor = "default";
      document.getElementById("expensePage").style.display = "block";
    }
    else if (this.router.url == "/view-income") {
      document.getElementById("incomeLabel").style.border = "1px solid navy";
      document.getElementById("incomeRoute").style.pointerEvents = "none";
      document.getElementById("incomeRoute").style.cursor = "default";
      document.getElementById("incomePage").style.display = "block";
    }
    else if (this.router.url == "/view-cashflow") {
      document.getElementById("cashflowLabel").style.border = "1px solid navy";
      document.getElementById("cashflowRoute").style.pointerEvents = "none";
      document.getElementById("cashflowRoute").style.cursor = "default";
      document.getElementById("cashflowPage").style.display = "block";
    }
  }

  public deleteExpense(id, expense){
    let resp= this.service.deleteExpense(id, expense);
    resp.subscribe((data)=>this.expenses=data);
  }
   
  public getExpensesByCategory(){
    let resp= this.service.selectExpensesByCategory(this.category);
    console.log(this.category)
    resp.subscribe((data)=>this.expenses=data);
  } 

  public formatDate(x) {
    var dt = new Date(x);

    var year = dt.getFullYear();
    var month = (1 + dt.getUTCMonth()).toString();
    month = month.length > 1 ? month : '0' + month;

    var day = dt.getUTCDate().toString();
    day = day.length > 1 ? day : '0' + day;
    
    return month + '/' + day + '/' + year;
  }

  public formatDateMontly(x) {
    var dt = new Date(x);

    var year = dt.getFullYear();
    var month = (1 + dt.getUTCMonth()).toString();
    month = month.length > 1 ? month : '0' + month;
    
    return month + '/' + year;
  }

  public getAllExpenses() {
    let getAllExpenses = this.service.selectAllExpenses();
    getAllExpenses.subscribe((data)=>this.expenses=data);

    this.dateFormatted = []
    getAllExpenses.subscribe((data: any[])=>{

      data.forEach((x)=> {
        var dt = new Date(x.date);

        var year = dt.getFullYear();
        var month = (1 + dt.getUTCMonth()).toString();
        month = month.length > 1 ? month : '0' + month;

        var day = dt.getUTCDate().toString();
        day = day.length > 1 ? day : '0' + day;
        
        this.dateFormatted.push(month + '/' + day + '/' + year);
      })
    });

  }

  public monthlyExpenseData() {  
    this.DateMonthlyData = []
    this.CostMonthlyData = []
    for (let i = 0; i < this.DateMonthly.length; i++) {
      if(!this.DateMonthlyData.includes(this.DateMonthly[i])) {
        this.DateMonthlyData.push(this.DateMonthly[i])
        this.CostMonthlyData.push(this.Cost[i])
      }
      else {
        var idx = this.DateMonthlyData.indexOf(this.DateMonthly[i])
        this.CostMonthlyData[idx] += this.Cost[i]
      }
    }

    this.CostMonthlyDataLabel = []
    for (let i = 0; i < this.DateMonthlyLabel.length; i++) {
      for (let j = 0; j < this.DateMonthlyData.length; j++) {
        if(this.DateMonthlyLabel[i] == this.DateMonthlyData[j]) {
          this.CostMonthlyDataLabel[i] = this.CostMonthlyData[j]
        }
        else {
          this.CostMonthlyDataLabel[j] = 0
        }

      }
    }
  }

  public graphData() {
    this.CategoryData = []
    this.CostData = []
    for (let i = 0; i < this.Category.length; i++) {
      if(!this.CategoryData.includes(this.Category[i])) {
        this.CategoryData.push(this.Category[i])
        this.CostData.push(this.Cost[i])
      }
      else {
        var idx = this.CategoryData.indexOf(this.Category[i])
        this.CostData[idx] += this.Cost[i]
      }
    }

    for (let i = 0; i < this.CostData.length; i++) {
      this.CostData[i] = this.CostData[i].toFixed(2);
    }
  
  }

  public DoughnutChart: Chart

  public doughnutChart() {
    this.Cost = []
    this.Category = []
    this.service.selectAllExpenses().subscribe((result: any[]) => {  
      result.forEach((x) => {  
        this.Cost.push(x.cost);  
        this.Category.push(x.category);  
      });  
      this.graphData();

      if (this.DoughnutChart != null) {
        this.DoughnutChart.destroy()
      }

      this.DoughnutChart = new Chart('canvasDoughnut', {  
        type: 'doughnut',  
        data: {  
          labels: this.CategoryData,  
          datasets: [  
            {  
              data: this.CostData,  
              borderColor: '#3cba9f',  
              backgroundColor: [  
                "#3cb371",  
                "#0000FF",  
                "#9966FF",  
                "#4C4CFF",  
                "#00FFFF",  
                "#f990a7",  
                "#aad2ed",  
                "#FF00FF",  
                "Blue",  
                "Red",  
                "Blue"  
              ],  
              fill: true  
            }  
          ]  
        },  
        options: {  
          cutoutPercentage: 70,
          title: {
            display: true,
            text: 'Expenses'
          },
          legend: {  
            display: false  
          },  
          scales: {  
            xAxes: [{  
              display: false  
            }],  
            yAxes: [{  
              display: false  
            }],  
          }  
        }  
      });  
    });  
  }

  public graphLineData() {
    this.DateData = []
    this.CostData = []
    for (let i = 0; i < this.Date.length; i++) {
      if(!this.DateData.includes(this.Date[i])) {
        this.DateData.push(this.Date[i])
        this.CostData.push(this.Cost[i])
      }
      else {
        var idx = this.DateData.indexOf(this.Date[i])
        this.CostData[idx] += this.Cost[i]
      }
    }
  }
  public LineChart: Chart

  public lineChart() {
    this.Date = []
    this.Cost = []
    this.DateMonthlyLabel = []
    this.DateMonthly = []

    this.service.selectAllExpenses().subscribe((result: any[]) => {  
      result.forEach((x) => {  
        this.Cost.push(x.cost);  
        this.Date.push(this.formatDate(x.date));  
        this.DateMonthly.push(this.formatDateMontly(x.date));  
      });  

      var startDate = new Date(this.Date[0])
      var endDate = new Date(this.Date[this.Date.length-1])

      while(startDate <= endDate) {
        this.DateMonthlyLabel.push(this.formatDateMontly(new Date(startDate)))
        startDate.setDate(startDate.getDate() + 1)
      }

      var xaxis;
      var yaxis;

      switch(this.apply) { 
        case "": { 
          this.graphLineData();
          xaxis = this.DateData;
          yaxis = this.CostData; 
          break; 
        } 
        case "YTD": { 
          this.monthlyExpenseData();
          xaxis = this.DateMonthlyLabel;
          yaxis = this.CostMonthlyDataLabel;
          break; 
        } 
        default: { 
           //statements; 
           break; 
        } 
     } 

      if (this.LineChart != null) {
        this.LineChart.destroy()
      }

      this.LineChart = new Chart('canvasLine', {  
        type: 'line',  
        data: {  
          labels: xaxis,  
          datasets: [  
            {  
              data: yaxis,  
              borderColor: '#3cb371',  
              backgroundColor: "#0000FF",  
            }  
          ]  
        },  
        options: {  
          legend: {  
            display: false  
          },  
          scales: {  
            xAxes: [{  
              display: true  
            }],
            yAxes: [{  
              display: true  
            }],  
          }  
        }  
      });  
    });  
  }

}
