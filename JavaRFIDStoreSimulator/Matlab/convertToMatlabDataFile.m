%%read txt file and convert it to matlab data file

clc, clear all

FILENAME = '1goodsAreaList.txt'; %goodsAreaList.txt  purchaseGoodsNo.txt  pathList.txt wayBackToEntryList.txt
%[fid, errmsg] = fopen(FILENAME,'r');
% goodsAreaList = textread(FILENAME, '%s', 'delimiter', '\n','whitespace', '');
% goodsAreaList2 = importdata(FILENAME,' ' )
table = readtable(FILENAME)
