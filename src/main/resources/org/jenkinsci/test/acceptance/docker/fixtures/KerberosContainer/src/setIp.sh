echo -n "ADDRESS=" > /.dockerenv
hostname -I > /.dockerenv
ADDRESS=`hostname -I`
export ADDRESS=$ADDRESS
echo "ADDRESS=$ADDRESS" > /.dockerenv