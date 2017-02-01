function onOpen() {
  var ui = SpreadsheetApp.getUi();
  // Or DocumentApp or FormApp.
  ui.createMenu('Execute Script')
      .addItem('Make Report', 'makeReport')
      .addToUi();
}
function makeReport() {
  var sheet = SpreadsheetApp.getActiveSheet();
  var name = sheet.getName();
  //var sheet = SpreadsheetApp.openByUrl("<sheet link>");
  //var name=sheet.getName();
  var data = sheet.getDataRange().getValues();
  var rows = sheet.getLastRow();
  //Logger.log(data);
  var docURL = DriveApp.getFileById('<doc Id>').makeCopy(name).getUrl();
  var doc = DocumentApp.openByUrl(docURL);
  var body = doc.getBody();
  var tables = body.getTables();
  var table = tables[0];
  //var cell0 = tables[0].getRow(1).getCell(0).getAttributes();
  //var cell1 = tables[0].getRow(1).getCell(1).getAttributes();
  //cell1.backgroundColor='#ffffff';
  //var cell2 = tables[0].getRow(1).getCell(2).getAttributes();
  var tr;var td;var flag=0;
  //transfer data from sheet to doc table according to the set standard
  for (i=1;i<rows;i++){
    if (data[i][2]!=''){
      if(i>1){
        table = tables[0].copy();
        body.appendTable(table);
      }
      tr = table.getRow(0);
      tr.getCell(0).setText(data[i][2]).editAsText().setForegroundColor('#ffffff');
      tr = table.getRow(1);
      tr.getCell(2).setText(data[i][4]);
      tr = table.getRow(2);
      tr.getCell(2).setText(data[i][5]);
      tr = table.getRow(4);
      tr.getCell(2).setText(data[i][6]);
      tr = table.getRow(5);
      tr.getCell(2).setText(data[i][1]);
      setSeverityColor(tr.getCell(2));
      tr = table.getRow(6);
      tr.getCell(2).setText(data[i][7]);
      tr = table.getRow(8);
      tr.getCell(2).setText(data[i][3]);
      tr = table.getRow(12);
      tr.getCell(2).setText(data[i][8]);
      tr = table.getRow(14);
      tr.getCell(2).setText(data[i][9]);
      flag++;
    }
    else{
      //Logger.log(flag);
      tables = body.getTables();
      table = tables[flag-1];
      var url = table.getRow(1).getCell(2).editAsText();
      url.appendText("\n"+data[i][4]);
      var parameter = table.getRow(2).getCell(2).editAsText();
      parameter.appendText("\n"+data[i][5]);
    }
  }
  formatTable(body.getTables(),flag);
  //var table = tables[0];
  //var tr = table.getRow(1);
  //tr.getCell(2).setText("hi");
  var finalURL = doc.getUrl();
  Logger.log(finalURL);
  showPopup(finalURL);
}

function formatTable(tables,flag){
  var table;
  for (i=0;i<flag;i++){
    table = tables[i];
    table.editAsText().setFontFamily('Arial');
    table.editAsText().setFontSize(12);
  }
}

function setSeverityColor(td){
  //td.replaceText(" .\n", replacement)
  text = td.getText();
  if(text=="High" || text=="high") td.setForegroundColor('#FF0000');
  else if (text=="Medium" || text=="medium") td.setForegroundColor('#FFA500');
  else if (text=="Low" || text=="low") td.setForegroundColor('#00FF00');
  else td.setForegroundColor('#0000FF');
}

function showPopup(url){
  var ui = SpreadsheetApp.getUi(); 
  var result = ui.alert(
     'Your Report is ready',
    'Follow the link: '+url,
      ui.ButtonSet.OK);
}
