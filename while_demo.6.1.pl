#! /usr/bin/perl

# Add up some odd numbers
print "Enter a value for count: ";
$count = <>;
print "Enter a value for max: ";
$max = <>;
$max;
$sum = 0;
$increment = 3; #determines what to increment count by
$max = 8; #determines how long the while loop will run

while( $count < 10 ){
  print $count, "\n";
  $sum += $count;
  $count += 1;
}

print "The final count: $count\n";
print "The sum total was: $sum\n";

$count = 0;

while($count < $max)
{
	print $count, "\n";
	$count += $increment;
}

print "New count is: $count\n";

exit;

