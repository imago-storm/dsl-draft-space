use strict;
use warnings;
use ElectricCommander;
use Data::Dumper;
use Benchmark qw(:all);
use Getopt::Long;

my $ec = ElectricCommander->new;
my $dsl = '';

my ($count, $parallel, $dslFile);
GetOptions('count=i' => \$count, 'parallel=i' => \$parallel, "file=s" => \$dslFile) or die;

open my $fh, $dslFile or die $!;
$dsl = join('' => <$fh>);
close $fh;

$parallel ||= 1;
print "Spawning $parallel children\n";

timethese($count, {'DSL Evaluation' => sub {
    for my $i (1 .. $parallel) {
        my $pid;
        next if $pid = fork();


        my $xpath = $ec->evalDsl({
            dsl => $dsl
        });

        print Dumper "PID: $$, child number $i";
        exit;
    }

    1 while (wait() != -1);
}});

