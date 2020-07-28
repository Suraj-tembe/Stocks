# Stocks

We have a set of 3 csv files. These files represent daily stock data values for a symbol - which is the stock symbol. e.g. Infosys has a symbol of INFY. Check the given css files. 
We need to calculate the daily range = high - low for each stock
 
Variation 1.
We need to calculate the DATR(daily average trading range) = An average of above field (range)  for previous available values from the CSV. If no value available previously it is just the range as above.
 
Variation 2.
We need to calculate a running total of the range for each day for each stock. Ordered by timestamp field in the csv
 
Variation 3.
We need to calculate a running average of the range for each day for each stock. Ordered by timestamp field in the csv
 
Then we need to create a single csv file in which we add the above 2 calculated fields for each row.
