ADDRESS=`hostname -I`
export ADDRESS=$ADDRESS
echo "ADDRESS=$ADDRESS" > /.dockerenv